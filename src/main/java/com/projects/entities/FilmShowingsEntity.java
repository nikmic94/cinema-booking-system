package com.projects.entities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a group of {@link FilmShowing} instances.
 * This collection is populated via a YAML file and no additions should be made during runtime.
 *
 * Map Key: {@link  FilmShowing#id}
 * Values: {@link FilmShowing#id} instance
 */
@Configuration
@ConfigurationProperties(prefix="default-film-showings")
public class FilmShowingsEntity {
    private Map<Long, FilmShowing> filmShowings;

    public FilmShowingsEntity() {}

    @PostConstruct
    public void setDateTime(){
        filmShowings.values().stream()
                             .filter(filmShowing -> filmShowing.getDateTimeStr() != null)
                             .forEach(filmShowing -> filmShowing.setDateTime(LocalDateTime.parse(filmShowing.getDateTimeStr(),
                                                                                                 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
    }

    public Map<Long, FilmShowing> getFilmShowings() {
        return filmShowings;
    }

    public void setFilmShowings(Map<Long, FilmShowing> filmShowings) {
        this.filmShowings = filmShowings;
    }

    public static class FilmShowing {
        private Long id;
        private Long filmId;
        private Long theatreId;
        private List<Long> bookingIds = new ArrayList<>();
        private String dateTimeStr;
        private LocalDateTime dateTime;

        public FilmShowing() {}

        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public Long getFilmId() {
            return filmId;
        }

        public void setFilmId(final Long filmId) {
            this.filmId = filmId;
        }

        public Long getTheatreId() {
            return theatreId;
        }

        public void setTheatreId(final Long theatreId) {
            this.theatreId = theatreId;
        }

        public List<Long> getBookingIds() {
            return bookingIds;
        }

        public void setBookingIds(final List<Long> bookingIds) {
            this.bookingIds = bookingIds;
        }

        public String getDateTimeStr() {
            return dateTimeStr;
        }

        public void setDateTimeStr(final String dateTimeStr) {
            this.dateTimeStr = dateTimeStr;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(final LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return "FilmShowing{" +
                    "id=" + id +
                    ", filmId=" + filmId +
                    ", theatreId=" + theatreId +
                    ", bookingIds=" + bookingIds +
                    ", dateTimeStr='" + dateTimeStr + '\'' +
                    ", dateTime=" + dateTime +
                    '}';
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final FilmShowing that = (FilmShowing) o;

            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
