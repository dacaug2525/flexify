using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class User
{
    public int Uid { get; set; }

    public string? Uname { get; set; }

    public string? Password { get; set; }

    public string? Fname { get; set; }

    public string? Lname { get; set; }

    public string? Email { get; set; }

    public string? Contact { get; set; }

    public string? Gender { get; set; }

    public int Rid { get; set; }

    public virtual ICollection<Member> Members { get; set; } = new List<Member>();

    public virtual ICollection<Trainer> Trainers { get; set; } = new List<Trainer>();
}
