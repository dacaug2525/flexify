using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class User
{
    public int Uid { get; set; }

    public string Uname { get; set; } = null!;
    public string Password { get; set; } = null!;
    public string Fname { get; set; } = null!;
    public string Lname { get; set; } = null!;
    public string Email { get; set; } = null!;
    public string Contact { get; set; } = null!;
    public string Gender { get; set; } = null!;

    public int Rid { get; set; }

    public virtual ICollection<Member> Members { get; set; } = new List<Member>();

    public virtual ICollection<Trainer> Trainers { get; set; } = new List<Trainer>();

    public virtual Trainer Trainer { get; set; }

}
