using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql.Scaffolding.Internal;

namespace TrainerPart.Models;

public partial class TrainerDbContext : DbContext
{
    public TrainerDbContext()
    {
    }

    public TrainerDbContext(DbContextOptions<TrainerDbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Member> Members { get; set; }

    public virtual DbSet<MemberAttendence> MemberAttendences { get; set; }

    public virtual DbSet<MemberProgress> MemberProgresses { get; set; }

    public virtual DbSet<MemberTrainerAssignment> MemberTrainerAssignments { get; set; }

    public virtual DbSet<Trainer> Trainers { get; set; }

    public virtual DbSet<TrainerSpecialization> TrainerSpecializations { get; set; }

    public virtual DbSet<TrainingTable> TrainingTables { get; set; }

    public virtual DbSet<User> Users { get; set; }

    public virtual DbSet<WorkoutSchedule> WorkoutSchedules { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseMySql("server=localhost;database=flexifydb;user=root;password=root", Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.38-mysql"));

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .UseCollation("utf8mb4_0900_ai_ci")
            .HasCharSet("utf8mb4");

        modelBuilder.Entity<Member>(entity =>
        {
            entity.HasKey(e => e.Mid).HasName("PRIMARY");

            entity.ToTable("members");

            entity.HasIndex(e => e.Uid, "fk_uid_idx");

            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.Address)
                .HasMaxLength(255)
                .HasColumnName("address");
            entity.Property(e => e.Dob)
                .HasColumnType("datetime")
                .HasColumnName("dob");
            entity.Property(e => e.Height).HasColumnName("height");
            entity.Property(e => e.JoinDate)
                .HasColumnType("datetime")
                .HasColumnName("join_date");
            entity.Property(e => e.Status)
                .HasColumnType("enum('active','inactive')")
                .HasColumnName("status");
            entity.Property(e => e.Uid).HasColumnName("uid");
            entity.Property(e => e.Weight).HasColumnName("weight");

            entity.HasOne(d => d.UidNavigation).WithMany(p => p.Members)
                .HasForeignKey(d => d.Uid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_uid");
        });

        modelBuilder.Entity<MemberAttendence>(entity =>
        {
            entity.HasKey(e => e.AttendenceId).HasName("PRIMARY");

            entity.ToTable("member_attendence");

            entity.HasIndex(e => e.Mid, "memid_idx");

            entity.Property(e => e.AttendenceId)
                .ValueGeneratedNever()
                .HasColumnName("attendence_id");
            entity.Property(e => e.Date)
                .HasColumnType("datetime")
                .HasColumnName("date");
            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.Status)
                .HasColumnType("enum('present','absent')")
                .HasColumnName("status");

            entity.HasOne(d => d.MidNavigation).WithMany(p => p.MemberAttendences)
                .HasForeignKey(d => d.Mid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("memid");
        });

        modelBuilder.Entity<MemberProgress>(entity =>
        {
            entity.HasKey(e => e.ProgressId).HasName("PRIMARY");

            entity.ToTable("member_progress");

            entity.HasIndex(e => e.Mid, "m_id_idx");

            entity.Property(e => e.ProgressId).HasColumnName("progress_id");
            entity.Property(e => e.Bmi).HasColumnName("bmi");
            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.RecordedDate)
                .HasColumnType("datetime")
                .HasColumnName("recorded_date");
            entity.Property(e => e.Remark)
                .HasMaxLength(45)
                .HasColumnName("remark");
            entity.Property(e => e.Weight).HasColumnName("weight");

            entity.HasOne(d => d.MidNavigation).WithMany(p => p.MemberProgresses)
                .HasForeignKey(d => d.Mid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("m_id");
        });

        modelBuilder.Entity<MemberTrainerAssignment>(entity =>
        {
            entity.HasKey(e => e.AssignmentId).HasName("PRIMARY");

            entity.ToTable("member_trainer_assignment");

            entity.HasIndex(e => e.Mid, "fk_mid_idx");

            entity.HasIndex(e => e.Tid, "fk_tid_idx");

            entity.Property(e => e.AssignmentId).HasColumnName("assignment_id");
            entity.Property(e => e.AssignDate)
                .HasColumnType("datetime")
                .HasColumnName("assign_date");
            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.Tid).HasColumnName("tid");

            entity.HasOne(d => d.MidNavigation).WithMany(p => p.MemberTrainerAssignments)
                .HasForeignKey(d => d.Mid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_memid");

            entity.HasOne(d => d.TidNavigation).WithMany(p => p.MemberTrainerAssignments)
                .HasForeignKey(d => d.Tid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_tid");
        });

        modelBuilder.Entity<Trainer>(entity =>
        {
            entity.HasKey(e => e.Tid).HasName("PRIMARY");

            entity.ToTable("trainers");

            entity.HasIndex(e => e.Uid, "uid_idx");

            entity.Property(e => e.Tid).HasColumnName("tid");
            entity.Property(e => e.Experience).HasColumnName("experience");
            entity.Property(e => e.Salary)
                .HasPrecision(10, 2)
                .HasColumnName("salary");
            entity.Property(e => e.Uid).HasColumnName("uid");

            entity.HasOne(d => d.UidNavigation).WithMany(p => p.Trainers)
                .HasForeignKey(d => d.Uid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("uid");
        });

        modelBuilder.Entity<TrainerSpecialization>(entity =>
        {
            entity.HasKey(e => e.TrainingId).HasName("PRIMARY");

            entity.ToTable("trainer_specialization");

            entity.HasIndex(e => e.TrId, "tr_id_idx");

            entity.HasIndex(e => e.Tid, "trai_id_idx");

            entity.Property(e => e.TrainingId).HasColumnName("training_id");
            entity.Property(e => e.Description)
                .HasMaxLength(255)
                .HasColumnName("description");
            entity.Property(e => e.Tid).HasColumnName("tid");
            entity.Property(e => e.TrId).HasColumnName("tr_id");

            entity.HasOne(d => d.TidNavigation).WithMany(p => p.TrainerSpecializations)
                .HasForeignKey(d => d.Tid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("trai_id");

            entity.HasOne(d => d.Tr).WithMany(p => p.TrainerSpecializations)
                .HasForeignKey(d => d.TrId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("tr_id");
        });

        modelBuilder.Entity<TrainingTable>(entity =>
        {
            entity.HasKey(e => e.TrId).HasName("PRIMARY");

            entity.ToTable("training_table");

            entity.Property(e => e.TrId).HasColumnName("tr_id");
            entity.Property(e => e.Desc)
                .HasMaxLength(255)
                .HasColumnName("desc");
            entity.Property(e => e.TrName)
                .HasMaxLength(45)
                .HasColumnName("tr_name");
        });

        modelBuilder.Entity<User>(entity =>
        {
            entity.HasKey(e => e.Uid).HasName("PRIMARY");

            entity.ToTable("users");

            entity.HasIndex(e => e.Rid, "rid_idx");

            entity.HasIndex(e => e.Uname, "uname_UNIQUE").IsUnique();

            entity.Property(e => e.Uid).HasColumnName("uid");
            entity.Property(e => e.Contact)
                .HasMaxLength(255)
                .HasColumnName("contact");
            entity.Property(e => e.Email)
                .HasMaxLength(255)
                .HasColumnName("email");
            entity.Property(e => e.Fname)
                .HasMaxLength(255)
                .HasColumnName("fname");
            entity.Property(e => e.Gender)
                .HasMaxLength(255)
                .HasColumnName("gender");
            entity.Property(e => e.Lname)
                .HasMaxLength(255)
                .HasColumnName("lname");
            entity.Property(e => e.Password)
                .HasMaxLength(255)
                .HasColumnName("password");
            entity.Property(e => e.Rid).HasColumnName("rid");
            entity.Property(e => e.Uname).HasColumnName("uname");
        });

        modelBuilder.Entity<WorkoutSchedule>(entity =>
        {
            entity.HasKey(e => e.WorkoutId).HasName("PRIMARY");

            entity.ToTable("workout_schedule");

            entity.HasIndex(e => e.MemberId, "member_id_idx");

            entity.HasIndex(e => e.TrainerId, "trainer_id_idx");

            entity.Property(e => e.WorkoutId).HasColumnName("workout_id");
            entity.Property(e => e.Days).HasColumnName("days");
            entity.Property(e => e.MemberId).HasColumnName("member_id");
            entity.Property(e => e.TrainerId).HasColumnName("trainer_id");
            entity.Property(e => e.WorkoutDesc)
                .HasMaxLength(255)
                .HasColumnName("workout_desc");

            entity.HasOne(d => d.Member).WithMany(p => p.WorkoutSchedules)
                .HasForeignKey(d => d.MemberId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("memberid");

            entity.HasOne(d => d.Trainer).WithMany(p => p.WorkoutSchedules)
                .HasForeignKey(d => d.TrainerId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("trainerid");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
