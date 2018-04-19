using Partner.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Partner.Service
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class CalendarService : ICalendarService
    {
        private Dictionary<int, Event> events = new Dictionary<int, Event>();

        private int counter;

        public int ReceiveRequest(EventRequest eventRequest)
        {
            Console.WriteLine("ReceiveRequest: " + eventRequest);
            var e = BuildEvent(eventRequest);
            events.Add(counter, e);
            return counter;
        }

        public Event FindEventById(int identifier)
        {
            if (!events.ContainsKey(identifier))
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
                return null;
            }
            return events[identifier];
        }

        private Event BuildEvent(EventRequest request)
        {
            var e = new Event
            {
                Identifier = counter++,
                RegisterDate = DateTime.Now.ToString(),
                StartDate = request.StartDate,
                EndDate = request.EndDate,
                RoomID = request.RoomID,
                Status = EventStatus.Ok
            };
            return e;
        }
    }

    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class PaymentService : IPaymentService
    {
        private const string MagicKey = "896983"; // ASCII code for "YES"

        private Dictionary<int, Payment> accounts = new Dictionary<int, Payment>();
        private int counter;

        public int ReceiveRequest(PaymentRequest request)
        {
            Console.WriteLine("ReceiveRequest: " + request);
            var payment = BuildPayment(request);
            accounts.Add(counter, payment);
            return counter;
        }

        public Payment FindPaymentById(int identifier)
        {
            if (!accounts.ContainsKey(identifier))
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
                return null;
            }
            return accounts[identifier];
        }

        public List<int> GetAllPaymentIds()
        {
            return accounts.Keys.ToList();
        }

        private Payment BuildPayment(PaymentRequest request)
        {
            var payment = new Payment
            {
                Identifier = counter++,
                CreditCard = request.CreditCard,
                Amount = request.Amount
            };
            if (request.CreditCard.Contains(MagicKey))
            {
                payment.Status = PaymentStatus.Ok;
            }
            else
            {
                payment.Status = PaymentStatus.Ko;
            }
            payment.Date = DateTime.Now.ToString();
            return payment;
        }
    }
}
