using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Partner.Data
{
    [DataContract(Namespace = "http://partner/external/service/data/", Name = "EventRequest")]
    public class EventRequest
    {
        [DataMember]
        public string StartDate { get; set; }

        [DataMember]
        public string EndDate { get; set; }

        [DataMember]
        public List<string> Rooms { get; set; }

        override public string ToString()
        {
            return "EventRequest[" + StartDate + ", " + EndDate + ", " + string.Join("|", Rooms.ToArray()) + "]";
        }
    }

    [DataContract(Namespace = "http://partner/external/service/data/", Name = "Event")]
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
        public List<string> Rooms { get; set; }

        [DataMember]
        public EventStatus Status { get; set; }

        override public string ToString()
        {
            return "Event[Identifier:" + Identifier + ", RegisterDate:" + RegisterDate + ", StartDate:" + StartDate + ", EndDate:" + EndDate + ", Rooms:" + string.Join("|", Rooms.ToArray()) + "]";
        }
    }

    public enum EventStatus { Ok, Ko }

    public class Day
    {
        public DateTime TheDay { get; set; }

        private Dictionary<string, List<Room>> planning = new Dictionary<string, List<Room>>
        {
            { "8:00", new List<Room>() },
            { "9:00", new List<Room>() },
            { "10:00", new List<Room>() },
            { "11:00", new List<Room>() },
            { "12:00", new List<Room>() },
            { "13:00", new List<Room>() },
            { "14:00", new List<Room>() },
            { "15:00", new List<Room>() },
            { "16:00", new List<Room>() }
        };

        public Day(DateTime day)
        {
            TheDay = day;
        }

        public Dictionary<string, List<Room>> Planning {
            get
            {
                return planning;
            }
            set
            {
                planning = value;
            }
        }
    }

    [DataContract(Namespace = "http://partner/external/service/data/", Name = "Room")]
    public class Room
    {
        [DataMember]
        public string ID { get; set; }

        [DataMember]
        public int Capacity { get; set; }

        [DataMember]
        public TypeSalle Type { get; set; }
    }

    public enum TypeSalle { Amphi, Cours, TD }

    [DataContract(Namespace = "http://partner/external/service/data/", Name = "PaymentRequest")]
    public class PaymentRequest
    {
        [DataMember]
        public string CreditCard { get; set; }

        [DataMember]
        public double Amount { get; set; }

        override public string ToString()
        {
            return "PaymentRequest[" + CreditCard + ", " + Amount + "]";
        }
    }

    [DataContract(Namespace = "http://partner/external/service/data/", Name = "Payment")]
    public class Payment
    {
        [DataMember]
        public int Identifier { get; set; }

        [DataMember]
        public string CreditCard { get; set; }

        [DataMember]
        public double Amount { get; set; }

        [DataMember]
        public PaymentStatus Status { get; set; }

        [DataMember]
        public string Date { get; set; }
    }

    public enum PaymentStatus { Ok, Ko }
}
