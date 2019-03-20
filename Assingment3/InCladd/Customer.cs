namespace InCladd
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Customer : DbContext
    {
        public Customer()
            : base("name=Customer")
        {
        }

        public virtual DbSet<Customer_details> Customer_details { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Customer_details>()
                .Property(e => e.FirstName)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.LastName)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.StreetNumber)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.Street)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.city)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.Province)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.Country)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.postalCode)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.Phonenumber)
                .IsUnicode(false);

            modelBuilder.Entity<Customer_details>()
                .Property(e => e.EmailAddress)
                .IsUnicode(false);
        }
    }
}
