namespace TrainerPart.DTO_s
{
    public class TrainerProfileDto
    {
        // trainer table
        public int Tid { get; set; }
        public int Experience { get; set; }
        public decimal Salary { get; set; }

        // users table
       
        public string Uname { get; set; }
        public string Password { get; set; }   
        public string Fname { get; set; }
        public string Lname { get; set; }
        public string Email { get; set; }
        public string Contact { get; set; }
        public string Gender { get; set; }
      
    }
}
