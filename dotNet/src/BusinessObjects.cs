using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Partner.Data
{
    [DataContract(Namespace = "http://partner/external/calendar/data/", Name = "EventRequest")]
    public class EventRequest
    {
        [DataMember]
        public string StartDate { get; set; }

        [DataMember]
        public string EndDate { get; set; }

        [DataMember]
        public string RoomID { get; set; }

        override public string ToString()
        {
            return "EventRequest[" + StartDate + ", " + EndDate + ", " + RoomID + "]";
        }
    }

    [DataContract(Namespace = "http://partner/external/calendar/data/", Name = "Event")]
    public class Event
    {
        [DataMember]
        public int Identifier { get; set; }

        [DataMember]
        public string RegisterDate { get; set; }

        [DataMember]
        public string StartDate { get; set; }

        [DataMember]
        public string EndDate { get; set; }

        [DataMember]
        public string RoomID { get; set; }

        override public string ToString()
        {
            return "Event[Identifier:" + Identifier + ", RegisterDate:" + RegisterDate + ", StartDate:" + StartDate + ", EndDate:" + EndDate + ", ID:" + RoomID + "]";
        }
    }
}