using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class MemberTrainerAssignment
{
    public int AssignmentId { get; set; }

    public int Tid { get; set; }

    public int Mid { get; set; }

    public DateTime AssignDate { get; set; }

    public virtual Member MidNavigation { get; set; } = null!;

    public virtual Trainer TidNavigation { get; set; } = null!;
}
