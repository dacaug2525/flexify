package com.flexify.admin.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "feedback_id")
	    private Integer feedbackId;

	    @Column(name = "mid", nullable = false)
	    private Integer mid;

	    @Column(name = "tid", nullable = false)
	    private Integer tid;

	    @Column(length = 255)
	    private String comment;

	    @Column(nullable = false)
	    private LocalDateTime date;

	    @Column(nullable = false)
	    private Integer rating;

		public Feedback() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Feedback(Integer feedbackId, Integer mid, Integer tid, String comment, LocalDateTime date,
				Integer rating) {
			super();
			this.feedbackId = feedbackId;
			this.mid = mid;
			this.tid = tid;
			this.comment = comment;
			this.date = date;
			this.rating = rating;
		}

		public Integer getFeedbackId() {
			return feedbackId;
		}

		public void setFeedbackId(Integer feedbackId) {
			this.feedbackId = feedbackId;
		}

		public Integer getMid() {
			return mid;
		}

		public void setMid(Integer mid) {
			this.mid = mid;
		}

		public Integer getTid() {
			return tid;
		}

		public void setTid(Integer tid) {
			this.tid = tid;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}
}
