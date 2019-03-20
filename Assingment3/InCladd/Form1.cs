using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Net.Mail;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InCladd
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            DBConnection();
            Customer cut = new Customer();
            List<Customer_details> cst = new List<Customer_details>();
            cst = cut.Customer_details.ToList();

            lblprimarykey.Text = cst[0].Cust_id.ToString();
            txtFname.Text = cst[0].FirstName;
            txtLname.Text = cst[0].LastName;
            txtStrno.Text = cst[0].StreetNumber;
            txtStreet.Text = cst[0].Street;
            txtprovince.Text = cst[0].Province;
            txtCity.Text = cst[0].city;
            txtPhone.Text = cst[0].Phonenumber;
            txtCountry.Text = cst[0].Country;
            txtPostalCode.Text = cst[0].postalCode;
            txtEmailAddress.Text = cst[0].EmailAddress;
            if (validation(cst[0].postalCode, cst[0].Phonenumber, cst[0].EmailAddress) != null)
            {
                txtStatus.Text = validation(cst[0].postalCode, cst[0].Phonenumber, cst[0].EmailAddress).ToString();

            }

        }

        private void btnPrevious_Click(object sender, EventArgs e)
        {
            Customer cut = new Customer();
            List<Customer_details> cst = new List<Customer_details>();
            cst = cut.Customer_details.ToList();
            int i = 0;

            i = Convert.ToInt16(this.lblprimarykey.Text) - 1;


            if (i == cst.Count - 1 || i != 0)
            {
                int x = i - 1;
                lblprimarykey.Text = cst[x].Cust_id.ToString();
                txtFname.Text = cst[x].FirstName;
                txtLname.Text = cst[x].LastName;
                txtStrno.Text = cst[x].StreetNumber;
                txtStreet.Text = cst[x].Street;
                txtprovince.Text = cst[x].Province;
                txtCity.Text = cst[x].city;
                txtPhone.Text = cst[x].Phonenumber;
                txtCountry.Text = cst[x].Country;
                txtPostalCode.Text = cst[x].postalCode;
                txtEmailAddress.Text = cst[x].EmailAddress;

                if (validation(cst[x].postalCode, cst[x].Phonenumber, cst[x].EmailAddress) != null)
                {

                    txtStatus.Text = validation(cst[x].postalCode, cst[x].Phonenumber, cst[x].EmailAddress).ToString();

                }

            }
            else
            {

                lblerror.Text = "No Records to show";
                lblerror.Visible = true;
            }
        }

        private void btnNext_Click(object sender, EventArgs e)
        {
            Customer cut = new Customer();
            List<Customer_details> cst = new List<Customer_details>();
            cst = cut.Customer_details.ToList();
            int i = 0;

            i = Convert.ToInt16(this.lblprimarykey.Text) - 1;

            if (i < cst.Count - 1)
            {

                lblprimarykey.Text = cst[i + 1].Cust_id.ToString();
                txtFname.Text = cst[i + 1].FirstName;
                txtLname.Text = cst[i + 1].LastName;
                txtStrno.Text = cst[i + 1].StreetNumber;
                txtStreet.Text = cst[i + 1].Street;
                txtprovince.Text = cst[i + 1].Province;
                txtCity.Text = cst[i + 1].city;
                txtPhone.Text = cst[i + 1].Phonenumber;
                txtCountry.Text = cst[i + 1].Country;
                txtPostalCode.Text = cst[i + 1].postalCode;
                txtEmailAddress.Text = cst[i + 1].EmailAddress;

                if (validation(cst[i + 1].postalCode, cst[i + 1].Phonenumber, cst[i + 1].EmailAddress) != null)
                {

                    txtStatus.Text = validation(cst[i + 1].postalCode, cst[i + 1].Phonenumber, cst[i + 1].EmailAddress).ToString();

                }
            }
            else
            {
                lblerror.Text = "No Records to show";
                lblerror.Visible = true;
            }

        }

        private void DBConnection()
        {
            try
            {
                string connection = ConfigurationManager.ConnectionStrings["Customer"].ConnectionString;
                using (SqlConnection conn = new SqlConnection(connection))
                {
                    conn.Open();
                    Console.WriteLine("Done.");
                }
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.ToString());
            }
        }

        private string validation(string PostalCode, string PhoneNumber, string EmailAddress)
        {
            string errormessage = null;

            var postalCode = Regex.Match(PostalCode, @"^([a-zA-Z]\d[a-zA-Z]( )?\d[a-zA-Z]\d)$");
            if (!postalCode.Success)
            {
                errormessage = "Postal is not in a valid Format";

            }
            Regex regexPhoneNumber = new Regex(@"^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$");

            if (!regexPhoneNumber.IsMatch(PhoneNumber))
            {

                errormessage = "Phone number is not in a valid Format";

            }

            if (!IsValid(EmailAddress))
            {
                errormessage = "Email Address is not in a valid Format";

            }
            if (errormessage != null)
            {
                txtStatus.Visible = true;
                lblStatus.Visible = true;
            }
            else
            {
                txtStatus.Visible = false;
                lblStatus.Visible = false;

            }

            return errormessage;
        }
        public bool IsValid(string emailaddress)
        {
            try
            {
                MailAddress m = new MailAddress(emailaddress);

                return true;
            }
            catch (FormatException)
            {
                return false;
            }
        }

    }
}
