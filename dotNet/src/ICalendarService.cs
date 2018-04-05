using Partner.Data;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Web;

namespace Partner.Service
{
    [ServiceContract]
    public interface IEDTService
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
}
