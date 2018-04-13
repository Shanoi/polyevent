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

        [DataMember]
        public EventStatus Status { get; set; }

        override public string ToString()
        {
            return "Event[Identifier:" + Identifier + ", RegisterDate:" + RegisterDate + ", StartDate:" + StartDate + ", EndDate:" + EndDate + ", ID:" + RoomID + "]";
        }
    }

    public enum EventStatus { Ok, Ko }

    public class Day
    {
        public String Day { get; set; }

        private Dictionary<string, List<string>> planning = new Dictionary<string, List<string>>
        {
            { "8:00", new List<string>() },
            { "9:00", new List<string>() },
            { "10:00", new List<string>() },
            { "11:00", new List<string>() },
            { "12:00", new List<string>() },
            { "13:00", new List<string>() },
            { "14:00", new List<string>() },
            { "15:00", new List<string>() },
            { "16:00", new List<string>() },
            { "17:00", new List<string>() },
            { "18:00", new List<string>() }
        };

        public Day(string day)
        {
            Day = day;
        }

        public Dictionary<string, List<string>> Planning { get; set; }
    }
}