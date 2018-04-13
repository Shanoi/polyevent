using Partner.Data;
using System;
using System.Collections.Generic;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Partner.Service
{
    // The service is stateful, as it is only a Proof of Concept.
    // Services should be stateless, this is for demonstration purpose only.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class EDTService : IEDTService
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
            var e = new Event();
            e.Identifier = counter++;
            e.RegisterDate = DateTime.Now.ToString();
            e.StartDate = request.StartDate;
            e.EndDate = request.EndDate;
            e.RoomID = request.RoomID;
            e.Status = EventStatus.Ok;
            return e;
        }
    }
}