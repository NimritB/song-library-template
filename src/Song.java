import java.util.List;

public class Song {
    // Private instance variables
    private String title = "Unknown";
    private String artist = "Unknown Artist";
    private String date; // year of release
    private String len; // length of the song in seconds
    private String danceability; // danceability score
    private String obscene; // obscenity score
    private String shake_the_audience; // shakeability score
    private String loudness; // loudness level

    // Default constructor
    public Song() {}

    // Overloaded constructor
    public Song(String artist, String title, String date, String len, 
                String danceability, String obscene, 
                String shake_the_audience, String loudness) {
        this.artist = artist;
        this.title = title;
        this.date = date;
        this.len = len;
        this.danceability = danceability;
        this.obscene = obscene;
        this.shake_the_audience = shake_the_audience;
        this.loudness = loudness;
    }

    // toString for printing the object
    public String toString() {
        return artist + " - " + title;
    }

    // Getters for getting private instance variables
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public String getLen() {
        return len;
    }

    public String getDanceability() {
        return danceability;
    }

    public String getObscene() {
        return obscene;
    }

    public String getShake_the_audience() {
        return shake_the_audience;
    }

    public String getLoudness() {
        return loudness;
    }

    // Setters to update instance variables
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public static void main(String[] args) {
        // Sample list of songs (add actual songs as needed)
        List<Song> songs = List.of(
            new Song("Artist1", "Song1", "1982", "240", "0.8", "0.5", "0.9", "-5"),
            new Song("Artist2", "Song2", "1998", "180", "0.6", "0.3", "0.8", "-4"),
            new Song("Artist3", "Song3", "2011", "200", "0.75", "0.4", "0.85", "-3")
            // Add more songs as needed
        );

        int yearMatchCount = 0;
        int highDanceabilityCount = 0;
        int maxLength = 0;
        String longestSong = "";
        double minShakeability = 1.0;
        String leastShakeableSong = "";
        double maxLoudness = 0.0;
        String loudestSong = "";

        double[] obscenityTotals = new double[7];
        int[] obscenityCounts = new int[7];

        for (Song song : songs) {
            String yearStr = song.getDate();
            int year = Integer.parseInt(yearStr);
            double obscenity = Double.parseDouble(song.getObscene());

            // Track the number of songs from specific years
            if (year == 1982 || year == 1998 || year == 2011) {
                yearMatchCount++;
            }

            // Calculate obscenity levels per decade
            if (year >= 1950 && year <= 2019) {
                int decadeIndex = (year - 1950) / 10;
                obscenityTotals[decadeIndex] += obscenity;
                obscenityCounts[decadeIndex]++;
            }

            // Count songs with high danceability
            if (Double.parseDouble(song.getDanceability()) > 0.75) {
                highDanceabilityCount++;
            }

            // Identify the longest song
            int length = Integer.parseInt(song.getLen());
            if (length > maxLength) {
                maxLength = length;
                longestSong = song.getTitle();
            }

            // Identify the song with the lowest shakeability
            double shakeability = Double.parseDouble(song.getShake_the_audience());
            if (shakeability < minShakeability) {
                minShakeability = shakeability;
                leastShakeableSong = song.getTitle();
            }

            // Find the loudest song
            double loud = Double.parseDouble(song.getLoudness());
            if (loud > maxLoudness) {
                maxLoudness = loud;
                loudestSong = song.getTitle();
            }
        }

        // Output results
        System.out.println("Total songs: " + songs.size());
        System.out.println("Songs from 1982, 1998, or 2011: " + yearMatchCount);
        System.out.println("Songs with danceability > 0.75: " + highDanceabilityCount);
        System.out.println("Longest song: " + longestSong + " (Length: " + maxLength + ")");
        System.out.println("Least shakeable song: " + leastShakeableSong + " (Shakeability: " + minShakeability + ")");
        System.out.println("Loudest song: " + loudestSong + " (Loudness: " + maxLoudness + ")");

        String[] decades = {"1950-1959", "1960-1969", "1970-1979", "1980-1989", "1990-1999", "2000-2009", "2010-2019"};
        for (int i = 0; i < obscenityTotals.length; i++) {
            if (obscenityCounts[i] > 0) {
                double averageObscenity = obscenityTotals[i] / obscenityCounts[i];
                System.out.println("Average obscenity " + decades[i] + ": " + averageObscenity);
            }
        }
    }
}
