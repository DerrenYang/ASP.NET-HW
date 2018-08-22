using ASP.NET_HomeWork.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Timers;

namespace ASP.NET_HomeWork.Controllers
{
    public class UserController : Controller
    {
        // GET: User
        
        
        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Login(tCustomer p)
        {
            dbDemoDataContext db = new dbDemoDataContext();
            tCustomer c = db.tCustomer.FirstOrDefault(
               m => m.fEmail == p.fEmail);
            if (c == null)
            {
                ViewBag.kk = "郵件不可空白";
                return View();
            }

            if ((c.fEmail != p.fEmail) || (c.fPassword != p.fPassword))
            {
                if (c.fPassword == "")
                {
                    ViewBag.kk = "請輸入密碼";
                    return View();
                }
                else
                {
                    ViewBag.kk = "帳號或密碼輸入錯誤";
                    return View();
                }
            }
            else
            {
                Session[CDictionary.LOGIN_USER] = c;
                return RedirectToAction("../User/UserMain");
            }

        }

        public ActionResult SignUp()
        {

            return View(new UserData());
        }

        [HttpPost]
        public ActionResult SignUp(tCustomer p, UserData data)
        {

            if (string.IsNullOrEmpty(data.fName) || string.IsNullOrEmpty(data.fEmail))
            {
                ViewBag.pp = "暱稱和郵件不可空白";
                return View(data);
            }
            if (string.IsNullOrEmpty(data.fPassword) || data.fPassword != data.fPassword2)
            {
                ViewBag.pp = "密碼不可空白或確認錯誤";
                return View(data);
            }

            dbDemoDataContext db = new dbDemoDataContext();
            db.tCustomer.InsertOnSubmit(p);
            db.SubmitChanges();

          

            return RedirectToAction("../Home/Index");

        }

        public ActionResult Logout(tCustomer p)
        {

            Session.Clear();
            dbDemoDataContext db = new dbDemoDataContext();
            tCustomer c = db.tCustomer.FirstOrDefault(
               m => m.fId == p.fId);

            return RedirectToAction("../Home/Index");
        }

        public ActionResult UserMain(int? p)
        {
            dbDemoDataContext db = new dbDemoDataContext();
            tCustomer customer = (new dbDemoDataContext()).tCustomer.FirstOrDefault(m => m.fId == p);
            customer = Session[CDictionary.LOGIN_USER] as tCustomer;
            Timer timer = new Timer(5000);
            timer.AutoReset = true;
            timer.Enabled = true;
            if (customer != null)
            {
               

                ViewBag.Id = customer.fId.ToString() + ".jpg";
                ViewBag.Name = customer.fName;
                ViewBag.Phone = customer.fPhone;
                ViewBag.Email = customer.fEmail;
                ViewBag.Address = customer.fAddress;
                AllModel all = new AllModel();
                return View(all);            
            }              
            else
            {
                return RedirectToAction("../Home/Index");
            }
                
        }


        public ActionResult EditProfile()
        {

            return View();
        }

        [HttpPost]
        public ActionResult EditProfile(tCustomer p)
        {
            dbDemoDataContext db = new dbDemoDataContext();
            tCustomer customer = db.tCustomer.FirstOrDefault(
                m => m.fId == p.fId);
            if (customer != null)
            {
                customer.fName = p.fName;
                customer.fPhone = p.fPhone;
                customer.fEmail = p.fEmail;
                customer.fAddress = p.fAddress;
                db.SubmitChanges();
            }
            return RedirectToAction("UserMain");


        }

        public ActionResult NewMessage()
        {
            
            return View();
        }

        [HttpPost]
        public ActionResult NewMessage(tMessage m)
        {
            
           
            dbDemoDataContext db = new dbDemoDataContext();
            db.tMessage.InsertOnSubmit(m);
            db.SubmitChanges();

            return RedirectToAction("UserMain");


        }
    }
}