namespace InCladd
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Customer_details
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Cust_id { get; set; }

        [StringLength(50)]
        public string FirstName { get; set; }

        [StringLength(50)]
        public string LastName { get; set; }

        [StringLength(50)]
        public string StreetNumber { get; set; }

        [StringLength(50)]
        public string Street { get; set; }

        [StringLength(50)]
        public string city { get; set; }

        [StringLength(50)]
        public string Province { get; set; }

        [StringLength(50)]
        public string Country { get; set; }

        [StringLength(50)]
        public string postalCode { get; set; }

        [StringLength(50)]
        public string Phonenumber { get; set; }

        [StringLength(100)]
        public string EmailAddress { get; set; }
    }
}
