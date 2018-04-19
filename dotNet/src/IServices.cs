using Partner.Data;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Partner.Service
{
    [ServiceContract]
    public interface ICalendarService
    {
        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "eventbox",
            RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        int ReceiveRequest(EventRequest e);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "events/{identifier}",
                    ResponseFormat = WebMessageFormat.Json)]
        Event FindEventById(int identifier);
    }

    [ServiceContract]
    public interface IPaymentService
    {
        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "mailbox",
                    RequestFormat = WebMessageFormat.Json,
                    ResponseFormat = WebMessageFormat.Json)]
        int ReceiveRequest(PaymentRequest request);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "payments/{identifier}",
                    ResponseFormat = WebMessageFormat.Json)]
        Payment FindPaymentById(int identifier);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "payments",
                    ResponseFormat = WebMessageFormat.Json)]
        List<int> GetAllPaymentIds();
    }
}
