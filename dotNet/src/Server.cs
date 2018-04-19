using Partner.Service;
using System;
using System.IO;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Threading;

/**
 * References:
 * http://badger.developpez.com/tutoriels/dotnet/web-service-rest-avec-wcf-3-5/#LII-B
 *  - mono server.exe /standalone  => start the server in standalone mode
 *  - mono server.exe /port 9191   => starts the server on port 9191
 * Default is interactive mode on port 9090. Options can obviously be combined
 **/
public class Server
{
    // Attirbutes to support port selection and standalone mode
    private bool Standalone = false;
    private string Port = "9090";
    private string Locker = "server.LOCK";

    // Web Server used to host services
    private WebServiceHost CalendarHost;
    private WebServiceHost PaymentHost;

    /**
     * Start the web server on the given port, and host the expected service
     */
    public void start()
    {
        Console.WriteLine("Starting a WCF self-hosted .Net server... ");
        string url = "http://" + "localhost" + ":" + Port;

        WebHttpBinding b = new WebHttpBinding();
        CalendarHost = new WebServiceHost(typeof(CalendarService), new Uri(url + "/calendar"));
        PaymentHost = new WebServiceHost(typeof(PaymentService), new Uri(url + "/payment"));

        // Adding the service to the host
        CalendarHost.AddServiceEndpoint(typeof(ICalendarService), b, "");
        PaymentHost.AddServiceEndpoint(typeof(IPaymentService), b, "");

        // Starting the CalendarHost server
        CalendarHost.Open();
        PaymentHost.Open();
        Console.WriteLine("\nListening to " + "localhost" + ":" + Port + "/calendar\n" +
            "\nListening to " + "localhost" + ":" + Port + "/payment\n");

        if (Standalone)
        {
            lockServer();
        }
        else
        {
            interactive();
        }
    }

    /**
     * Stop the already started web server
     */
    private void stop()
    {
        CalendarHost.Close();
        Console.WriteLine("Server shutdown complete!");
    }

    /**
     * Read options fron the command line. Does not support inconsistent commands
     */
    public void configure(string[] args)
    {
        int standaloneIndex = Array.FindIndex(args, key => key == "/standalone");
        if (standaloneIndex != -1)
        {
            this.Standalone = true;
        }

        int portIndex = Array.FindLastIndex(args, key => key == "/port");
        if (portIndex != -1)
        {
            this.Port = args[portIndex + 1];
        }
    }

    /**
     * Start the server in interactive mode, waiting for Return to
     */
    private void interactive()
    {
        Console.WriteLine("Hit Return to shutdown the server.");
        Console.ReadLine();
        stop();
    }

    /**
     * Lock the server in standalone mode using a given file
     */
    private void lockServer()
    {
        File.Create(Locker);
        Console.WriteLine("Delete the lock file (" + Locker + ") to stop the server");
        watchforLockRemoval();
    }

    /**
     * Active polling of the file system (ugly) to check the existence of the lock file
     */
    private void watchforLockRemoval()
    {
        var shouldStop = false;
        while (!shouldStop)
        {
            Thread.Sleep(1000);
            if (!File.Exists(Locker))
            {
                shouldStop = true;
            }
        }
        stop();
    }

    /**
     * Main method, called with `mono server.exe`
     */
    public static void Main(string[] args)
    {
        var server = new Server();
        server.configure(args);
        server.start();
    }
}
