using Partner.Data;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Partner.Service
{
    [ServiceContract(Namespace = "http://partner/external/service/calendar/")]
    public interface ICalendar
    {
        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "rooms/{start_date}/{end_date}", ResponseFormat = WebMessageFormat.Json)]
        Dictionary<string, List<Room>> GetVacantRooms(string start_date, string end_date);
        // Format = H:mm_d-M-yyyy

        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "eventbox",
            RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        int ReceiveEventRequest(EventRequest e);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "events/{identifier}",
                    ResponseFormat = WebMessageFormat.Json)]
        Event FindEventById(string identifier);
    }

    [ServiceContract(Namespace = "http://partner/external/service/payment/")]
    public interface IPayment
    {
        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "mailbox",
                    RequestFormat = WebMessageFormat.Json,
                    ResponseFormat = WebMessageFormat.Json)]
        int ReceivePaymentRequest(PaymentRequest request);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "payments/{identifier}",
                    ResponseFormat = WebMessageFormat.Json)]
        Payment FindPaymentById(string identifier);

        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "payments",
                    ResponseFormat = WebMessageFormat.Json)]
        List<int> GetAllPaymentIds();
    }
}
