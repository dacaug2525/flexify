using Microsoft.EntityFrameworkCore;
using Steeltoe.Discovery.Client;
using TrainerPart.Models;

namespace TrainerPart
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // ---------------- SERVICES ----------------

            builder.Services.AddControllers()
                .AddJsonOptions(options =>
                {
                    options.JsonSerializerOptions.PropertyNameCaseInsensitive = true;
                });

            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            // DB Context
            builder.Services.AddDbContext<TrainerDbContext>(options =>
                options.UseMySql(
                    builder.Configuration.GetConnectionString("DefaultConnection"),
                    ServerVersion.AutoDetect(
                        builder.Configuration.GetConnectionString("DefaultConnection")
                    )
                )
            );

            // CORS
            builder.Services.AddCors(options =>
            {
                options.AddPolicy("AllowReact", policy =>
                {
                    policy
                        .WithOrigins("http://localhost:3000")
                        .AllowAnyHeader()
                        .AllowAnyMethod();
                });
            });

            // Add Steeltoe Discovery Client
            builder.Services.AddDiscoveryClient(builder.Configuration);

            var app = builder.Build();





            // ---------------- MIDDLEWARE ----------------

            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            // app.UseHttpsRedirection(); // OK to keep disabled for local dev

            app.UseRouting();

            app.UseCors("AllowReact");

            app.UseAuthorization();

            // Use Steeltoe Discovery Client
            app.UseDiscoveryClient();


            app.MapControllers();

            app.Run();
        }
    }
}
