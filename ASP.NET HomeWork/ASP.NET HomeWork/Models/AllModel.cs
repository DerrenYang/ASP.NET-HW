using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ASP.NET_HomeWork.Models
{
    public class AllModel
    {
        public IEnumerable<tCustomer> T1 { get; set; }
        public IEnumerable<tMessage> T2 { get; set; }

        public AllModel()
        {
            dbDemoDataContext db = new dbDemoDataContext();
            this.T1 = db.tCustomer.ToList();
            this.T2 = db.tMessage.ToList();
        }
      

    }
}