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
            Console.WriteLine("Service Calendar invoked with GetVacantRooms method with the following parameters:\n" +
                "\tstart_date: " + start_date + "\n\tend_date: " + end_date + "\n");
            Thread.CurrentThread.CurrentCulture = new CultureInfo("fr-FR");
            DateTime startDateTime = DateTime.Parse(start_date.Replace('_', ' ').Replace('-', '/'));
            DateTime endDateTime = DateTime.Parse(end_date.Replace('_', ' ').Replace('-', '/'));

            if (startDateTime.Ticks >= endDateTime.Ticks)
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.InternalServerError;
                return null;
            }
            else
            {
                Dictionary<string, List<Room>> result = new Dictionary<string, List<Room>>();

                Day startDay = new Day(new DateTime());
                Day endDay = new Day(new DateTime());

                foreach (Day day in Mock.days)
                {
                    if (startDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                    {
                        startDay.Planning = day.Planning;
                        startDay.TheDay = startDateTime;
                    }
                    if (endDateTime.ToShortDateString() == day.TheDay.ToShortDateString())
                    {
                        endDay.Planning = day.Planning;
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
        }

        public int ReceiveEventRequest(EventRequest eventRequest)
        {
            Console.WriteLine("Service Calendar invoked with ReceiveEventRequest method with the following parameters:\n" +
                "eventRequest: " + eventRequest + "\n");
            var e = BuildEvent(eventRequest);
            events.Add(counter, e);
            return counter;
        }

        public Event FindEventById(string identifier)
        {
            Console.WriteLine("Service Calendar invoked with FindEventById method with the following parameters:\n" +
                "identifier: " + identifier + "\n");
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
            };
            if (Mock.UpdateRooms(e.StartDate, e.EndDate, e.Rooms))
            {
                e.Status = EventStatus.Ok;
            }
            else
            {
                e.Status = EventStatus.Ko;
            }
            return e;
        }

        public Room GetRoomInfo(string id)
        {
            Console.WriteLine("Service Calendar invoked with GetRoomInfo method with the following parameters:\n" +
                "id: " + id + "\n");
            foreach (Room room in Mock.rooms)
            {
                if (room.ID.Equals(id))
                {
                    return room;
                }
            }

            WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
            return null;
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
            Console.WriteLine("Service Payment invoked with ReceivePaymentRequest method with the following parameters:\n" +
                "request: " + request + "\n");
            var payment = BuildPayment(request);
            accounts.Add(paymentCounter, payment);
            return paymentCounter;
        }

        public Payment FindPaymentById(string identifier)
        {
            Console.WriteLine("Service Payment invoked with FindPaymentById method with the following parameters:\n" +
                "identifier: " + identifier + "\n");
            if (!accounts.ContainsKey(Int32.Parse(identifier)))
            {
                WebOperationContext.Current.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
                return null;
            }
            return accounts[Int32.Parse(identifier)];
        }

        public List<int> GetAllPaymentIds()
        {
            Console.WriteLine("Service Payment invoked with GetAllPaymentIds method\n");
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
