using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.IO;

namespace ASP.NET_HomeWork.Controllers
{
    public class FileUploadController : Controller
    {
        // GET: FileUpload
        public ActionResult create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult create(HttpPostedFileBase photo,tCustomer p)
        {
           tCustomer customer = Session[CDictionary.LOGIN_USER] as tCustomer;
            string fileName = "";
            if(customer == null)
            {
                return RedirectToAction("../User/Login");
            }

            if(photo != null)
            {
                if(photo.ContentLength>0)//檔案大小>0
                {
                    fileName = customer.fId.ToString() + ".jpg"; ;
                    var path = Path.Combine(Server.MapPath("~/Photos"), fileName);
                    photo.SaveAs(path);
                }
            }

            return RedirectToAction("../User/UserMain");
        }

        public string ShowPhotos()
        {
            string show = "";
            DirectoryInfo dir = new DirectoryInfo(Server.MapPath("~/Photos"));

            FileInfo[] fInfo = dir.GetFiles();
            int n = 0;
            foreach(FileInfo result in fInfo)
            {
                n++;
                show += "<a href='../Photos/" + result.Name + "'target='_blank'><img src='../Photos/" + result.Name + "'width='90' height='60' border='0'></a>";
                if(n%4==0)
                {
                    show += "<p>";
                }
            }

            show += "<p><a href='create'>返回</a></p>";
            return show;
        }
    }
}