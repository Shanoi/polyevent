using Partner.Data;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Threading;

namespace Partner.Service
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, Namespace = "http://partner/external/service/calendar/")]
    public class CalendarService : ICalendar
    {
        private Dictionary<int, Event> events = new Dictionary<int, Event>();

        private int counter;

        public Dictionary<string, List<Room>> GetVacantRooms(string start_date, string end_date)
        {
            Thread.CurrentThread.CurrentCulture = new CultureInfo("fr-FR");
            DateTime startDateTime = DateTime.Parse(start_date.Replace('_', ' ').Replace('-', '/'));
            DateTime endDateTime = DateTime.Parse(end_date.Replace('_', ' ').Replace('-', '/'));

            Dictionary<string, List<Room>> result = new Dictionary<string, List<Room>>();

            Day startDay = new Day(new DateTime());
            Day endDay = new Day(new DateTime());

            foreach (Day day in Mock.days)
            {
                if (startDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                {
                    startDay = day;
                    startDay.TheDay = startDateTime;
                }
                if (endDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                {
                    endDay = day;
                    endDay.TheDay = endDateTime;
                    break;
                }
            }

            // Handle case where the event is held only one day.
            if (startDay.TheDay.ToShortDateString() == endDay.TheDay.ToShortDateString())
            {
                for (int i = startDateTime.Hour; i < endDateTime.Hour; ++i)
                {
                    result.Add(startDay.TheDay.ToShortDateString() + " " + i + ":00", startDay.Planning[i + ":00"]);
                }
            }
            else
            {
                for (int i = startDay.TheDay.Hour; i < 17; ++i)
                {
                    result.Add(startDay.TheDay.ToShortDateString() + " " + i + ":00", startDay.Planning[i + ":00"]);
                }

                startDay.TheDay = startDay.TheDay.AddDays(1);

                while (startDay.TheDay.ToShortDateString() != endDay.TheDay.ToShortDateString())
                {
                    for (int i = 8; i < 17; i++)
                    {
                        result.Add(startDay.TheDay.ToShortDateString() + " " + i + ":00", startDay.Planning[i + ":00"]);
                    }

                    startDay.TheDay = startDay.TheDay.AddDays(1);
                }

                for (int i = 8; i < endDay.TheDay.Hour; i++)
                {
                    result.Add(startDay.TheDay.ToShortDateString() + " " + i + ":00", startDay.Planning[i + ":00"]);
                }
            }

            return result;
        }

        public int ReceiveEventRequest(EventRequest eventRequest)
        {
            Console.WriteLine("ReceiveRequest: " + eventRequest);
            var e = BuildEvent(eventRequest);
            events.Add(counter, e);
            return counter;
        }

        public Event FindEventById(string identifier)
        {
            if (!events.ContainsKey(Int32.Parse(identifier)))
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
                return null;
            }
            return events[Int32.Parse(identifier)];
        }

        private Event BuildEvent(EventRequest request)
        {
            var e = new Event
            {
                Identifier = counter++,
                RegisterDate = DateTime.Now.ToString(),
                StartDate = request.StartDate,
                EndDate = request.EndDate,
                Rooms = request.Rooms,
                Status = EventStatus.Ok
            };
            return e;
        }
    }

    // Payment Service //
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, Namespace = "http://partner/external/service/payment/")]
    public class PaymentService : IPayment
    {
        private const string MagicKey = "896983"; // ASCII code for "YES"

        private Dictionary<int, Payment> accounts = new Dictionary<int, Payment>();
        private int paymentCounter;

        public int ReceivePaymentRequest(PaymentRequest request)
        {
            Console.WriteLine("ReceiveRequest: " + request);
            var payment = BuildPayment(request);
            accounts.Add(paymentCounter, payment);
            return paymentCounter;
        }

        public Payment FindPaymentById(string identifier)
        {
            if (!accounts.ContainsKey(Int32.Parse(identifier)))
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
                return null;
            }
            return accounts[Int32.Parse(identifier)];
        }

        public List<int> GetAllPaymentIds()
        {
            return accounts.Keys.ToList();
        }

        private Payment BuildPayment(PaymentRequest request)
        {
            var payment = new Payment
            {
                Identifier = paymentCounter++,
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
