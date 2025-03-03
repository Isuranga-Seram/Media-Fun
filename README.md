<br>
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#Version">Version</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
<h1 align="center">Media Fun - Media Player</h1>
<br>

![Media Fun Screen Shot1][product-screenshot-1]
screenshot - video interface
<br><br>
![Media Fun Screen Shot2][product-screenshot-2]
screenshot - audio interface
<br><br>

The Audio and Video Media Player is a versatile multimedia player that enables users to play both audio and video files. The application provides essential functionalities like play, pause, seek, volume control, file selection, and drag-and-drop support for a seamless user experience. Users can load various media formats, such as .mp3 for audio and .mp4 for video, and interact with the media through an intuitive and interactive interface.

_**Core Features:**_
1. **Audio and Video Playback:**

    * Supports playback of common media formats like .mp3 .flv .aac (audio) and .mp4 .avi .mkv .wav (video).
    * Allows users to toggle between audio and video playback seamlessly.


2. **File Selection:**

    * The user can select media files using the built-in Open button.
    * A file dialog allows users to choose audio or video files from their system for playback.


3. **Play, Pause, and Stop Controls:**

    * The Play button starts media playback.
    * The Pause button temporarily halts playback.
    * The Stop button stops playback and resets the media to the beginning.


4. **Seek and Progress Bar:**

    * The Seek Bar allows users to move through the media, skipping forward or backward.
    * The media position updates in real-time during playback, allowing for accurate scrubbing.


5. **Volume Control:**

    * The Volume Slider adjusts the media's audio volume.
    * Displays the current volume as a percentage in a Label.


6. **Duration Display:**

    * Displays the current playback time and total duration of the media in a Label.
    * Automatically updates as the media plays, showing the elapsed time and total time.


7. **Drag-and-Drop Support:**

    * Users can drag and drop media files directly onto the media player interface for immediate playback.
<br>

The project is designed using JavaFX for building the graphical user interface (GUI) and Java's MediaPlayer class to handle media playback. This project can be used as a standalone application for both personal and educational purposes, and it demonstrates how to integrate video and audio functionalities within a desktop application.
<br><br>

**_Technologies Used_**

1. JavaFX: 
   * For creating the graphical user interface, including buttons, sliders, labels, and media view components.


2. Java Media Framework (JMF): 
   * For handling media file playback and providing features such as pause, play, and seek functionalities.


3. FXML: 
   * A markup language used for defining the structure of the UI and separating the layout from the logic.


4. Event Handling:
   * JavaFX event-driven model is used to manage user interactions, including button clicks, drag-and-drop actions, and media playback control.



<p align="right">(<a href="#readme-top">back to top</a>)</p>


**_Key Functionalities_**

1. File Loading:
   * The user can load media files either by selecting the Open button and navigating through the file system or by dragging and dropping the media file onto the application window.


2. Playback Control:
   * The user can start, pause, and stop media playback with easy-to-use buttons.
   * The progress bar provides a visual representation of the current playback position.


3. Seek Control:
   * The Seek Bar allows users to jump to any point in the media by dragging the slider. The current playback time is updated as the user interacts with the seek bar.


4. Volume Control:
   * The volume can be adjusted by a slider, with the corresponding percentage value displayed on the screen.


5. File Formats:
   * Supports common audio and video formats such as .mp3 (audio) and .mp4 (video), and can be extended to support more formats with the appropriate libraries.

<hr>


### Built With

Here are the major frameworks/libraries used to bootstrap the application. Java language is used for the programming and the JavaFx and SceneBuilder are used for interface designing part.

![java-url]
<br><br>
![javafx][javafx-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- GETTING STARTED -->
## Getting Started

Make sure your Integrated Development Environments (IDEs) or Code Editors is working fine in the PC.


### Installation

_Below is an example of how you can installing and setting up your app._

1. Clone the repo
   ```sh
   git clone https://github.com/Isuranga-Seram/Media-Fun.git
   ```

2. Change git remote url to avoid accidental pushes to base project
   ```sh
   git remote set-url origin github_username/repo_name
   git remote -v # confirm the changes
   ```

<br>

### Dependencies
Make sure to check below dependencies are present in the POM.xml file.
<br>
```
<dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>23</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>23</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-media</artifactId>
            <version>23</version>
        </dependency>
    </dependencies>
```



<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- USAGE EXAMPLES -->
## Usage

The Media Fun app you developed can have a variety of uses. Here are some of the main use cases:

1. Personal Media Playback
2. Educational Tool for Learning Media Playback
3. Offline Media Player
4. Cross-Platform Media Player (Extended Use)
5. Audio and Video Testing Platform


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- CONTRIBUTING -->
## Contributing

Developed and maintained an audio/video media player as a solo project. This included all stages from concept and UI/UX design to coding, debugging, and deployment. Icons, images, and sample video clip are get from the third-party libraries.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- Version -->
## Version

0.0.1

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- LICENSE -->
## License

Copyright © 2025 Media Fun - media player. All Rights Reserved This project is licensed under the MIT License.
See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- CONTACT -->
## Contact

Isuranga Seram [email] - **_isurangaseram@gmail.com_**

Project Link: [https://github.com/Isuranga-Seram/Media-Fun.git](https://github.com/Isuranga-Seram/Media-Fun.git)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<hr>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* First and foremost, I acknowledge myself as the sole developer of this project. I took full responsibility for all aspects of the application, from initial planning and design to coding, testing, and deployment. This project allowed me to hone my skills in software development, particularly in using JavaFX for GUI design and Java's MediaPlayer for media playback.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot-1]: img/screenshot-video.png
[product-screenshot-2]: img/screenshot-audio.png
[java]: https://www.java.com/en/
[java-url]: https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/121px-Java_programming_language_logo.svg.png
[javafx]: https://openjfx.io/
[javafx-url]: https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/JavaFX_Logo.png/220px-JavaFX_Logo.png
