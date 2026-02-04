using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql.Scaffolding.Internal;
using TrainerPart.DTO_s;

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

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)=> optionsBuilder.UseMySql("server=localhost;database=flexifydb;user=root;password=root", Microsoft.EntityFrameworkCore.ServerVersion.Parse("8.0.38-mysql"));

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .UseCollation("utf8mb4_0900_ai_ci")
            .HasCharSet("utf8mb4");

        // ================= MEMBER =================
        modelBuilder.Entity<Member>(entity =>
        {
            entity.HasKey(e => e.Mid).HasName("PRIMARY");

            entity.ToTable("members");

            entity.HasIndex(e => e.Uid, "fk_uid_idx");

            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.Address).HasMaxLength(255).HasColumnName("address");
            entity.Property(e => e.Dob).HasColumnType("datetime").HasColumnName("dob");
            entity.Property(e => e.Height).HasColumnName("height");
            entity.Property(e => e.JoinDate).HasColumnType("datetime").HasColumnName("join_date");
            entity.Property(e => e.Status).HasColumnType("enum('active','inactive')").HasColumnName("status");
            entity.Property(e => e.Uid).HasColumnName("uid");
            entity.Property(e => e.Weight).HasColumnName("weight");

            entity.HasOne(d => d.UidNavigation)
                .WithMany(p => p.Members)
                .HasForeignKey(d => d.Uid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_uid");
        });

        // ================= MEMBER ATTENDENCE =================
        modelBuilder.Entity<MemberAttendence>(entity =>
        {
            entity.HasKey(e => e.AttendenceId).HasName("PRIMARY");

            entity.ToTable("member_attendence");

            entity.Property(e => e.AttendenceId)
                .ValueGeneratedNever()
                .HasColumnName("attendence_id");

            entity.Property(e => e.Date)
                .HasColumnType("datetime")
                .HasColumnName("date");

            entity.Property(e => e.Mid)
                .HasColumnName("mid");

            entity.Property(e => e.Status)
                .HasConversion<string>() // ✅ ENUM FIX (CORRECT PLACE)
                .HasColumnType("enum('present','absent')")
                .HasColumnName("status");

            entity.HasOne(d => d.MidNavigation)
                .WithMany(p => p.MemberAttendences)
                .HasForeignKey(d => d.Mid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("memid");
        });

        // ================= MEMBER PROGRESS =================
        modelBuilder.Entity<MemberProgress>(entity =>
        {
            entity.HasKey(e => e.ProgressId).HasName("PRIMARY");

            entity.ToTable("member_progress");

            entity.HasIndex(e => e.Mid, "m_id_idx");

            entity.Property(e => e.ProgressId).HasColumnName("progress_id");
            entity.Property(e => e.Bmi).HasColumnName("bmi");
            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.RecordedDate).HasColumnType("datetime").HasColumnName("recorded_date");
            entity.Property(e => e.Remark).HasMaxLength(45).HasColumnName("remark");
            entity.Property(e => e.Weight).HasColumnName("weight");

            entity.HasOne(d => d.MidNavigation)
                .WithMany(p => p.MemberProgresses)
                .HasForeignKey(d => d.Mid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("m_id");
        });

        // ================= MEMBER TRAINER ASSIGNMENT =================
        modelBuilder.Entity<MemberTrainerAssignment>(entity =>
        {
            entity.HasKey(e => e.AssignmentId).HasName("PRIMARY");

            entity.ToTable("member_trainer_assignment");

            entity.Property(e => e.AssignmentId).HasColumnName("assignment_id");
            entity.Property(e => e.AssignDate).HasColumnType("datetime").HasColumnName("assign_date");
            entity.Property(e => e.Mid).HasColumnName("mid");
            entity.Property(e => e.Tid).HasColumnName("tid");
        });

        // ================= TRAINER =================
        modelBuilder.Entity<Trainer>(entity =>
        {
            entity.HasKey(e => e.Tid).HasName("PRIMARY");
            entity.ToTable("trainers");
            entity.Property(e => e.Tid).HasColumnName("tid");
        });

        // ================= USER =================
        modelBuilder.Entity<User>(entity =>
        {
            entity.HasKey(e => e.Uid).HasName("PRIMARY");
            entity.ToTable("users");
            entity.Property(e => e.Uid).HasColumnName("uid");
        });

        // ================= WORKOUT SCHEDULE =================
        modelBuilder.Entity<WorkoutSchedule>(entity =>
        {
            entity.HasKey(e => e.WorkoutId).HasName("PRIMARY");
            entity.ToTable("workout_schedule");
            entity.Property(e => e.WorkoutId).HasColumnName("workout_id");

            entity.HasOne(w => w.Member)
      .WithMany(m => m.WorkoutSchedules)
      .HasForeignKey(w => w.MemberId);


            entity.HasOne(w => w.Trainer)
                  .WithMany(t => t.WorkoutSchedules)
                  .HasForeignKey(w => w.TrainerId);
        });

        // ================= TRAINING TABLE =================
        modelBuilder.Entity<TrainingTable>(entity =>
        {
            entity.ToTable("training_table");

            entity.HasKey(e => new { e.TrId});

            entity.Property(e => e.TrId).HasColumnName("tid");
          
        });

        modelBuilder.Entity<TrainerSpecialization>()
        .HasKey(ts => new { ts.Tid, ts.TrainingId });

        base.OnModelCreating(modelBuilder);

        OnModelCreatingPartial(modelBuilder);
    }


    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
