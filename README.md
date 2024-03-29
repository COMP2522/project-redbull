#Comp 2522: Project Redbull
##Set C

# Project Members:
- **Cam: Front End Wizard**
- **Kavin: Designer
- **Kale: The Game Creation God**
- **Adam: The Test Giver**
- **Harrison: Junior Code Monkey**

<b>#Project Description</b>

This project is everything you loved about the original snake but more! We shook up the repetitive gameplay loop of the original snake with
innovative new levels, more complex UI, smoother gameplay and animations, and enemies! We hope you enjoy our take on Snake.


#Member Contributions

## Cameron Fung
<h3>Front end Designer</h3>
<ul>
        <li> Designed the architecture for the UI.</li>
        <li> Implemented the UI.</li>
        <li> Contributed to the high level design of the entire system.</li>
</ul> 
<h4> Individual pitch</h4>
<p>
I created the design for the archetecture for the UI.
The main idea that I wanted to implement is the composite pattern. 
This was done by creating the UIElement abstract class and the Frame class.
</p>

<p>
The UIElement class is the base class for everything that shows up in the UI. It has a draw method that is called
every frame.
The Frame class is a UIElement that contains other UIElements. It is used to create a hierarchy of UIElements.
</p>

<h4>How to create a new page</h4>
<p> 
If you want to create a new page you need to create a new class that extends the Frame class.
</p>

```aidl
public class NewCustomPage extends Frame {
    public NewCustomPage(PApplet parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);
    }
}
```

<p>Then you need to add the new class to the UIManager constructor.</p>

```aidl
this.pages = new UIComponent[] {
    new MenuPage(parent, x , y , width , height , 0, ""),
    new LevelSelector(parent, x + sideBarWidth, y, width- sideBarWidth, height, levelNames),
    new HighScoreLevels(parent, x + sideBarWidth, y, width - sideBarWidth, height, levelNames),
    new HighScoreBoard(parent, x, y, width, height, db),
    new InGameUI(parent, x, y, width, height)
    new NewCustomPage(parent, x, y, width, height)
};
```


<p>You will need to add buttons that navigate to and from the page. You can do this by adding a button in any page.
In this example I will add it to the menu page.
</p>

```aidl
  
// in the class declaration
private Button goToNewPageButton;

public MenuPage(Window parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height);
    // Initializing the button in the page
    float playButtonWidth = 300;
    goToNewPageButton = new Button(parent, x + width/2 - playButtonWidth / 2, y + height/2, playButtonWidth, 75, "PLAY");
    
    // adding the button to the components of the page
    add(goToNewPageButton);
}
```

<p> Finally add the new button to the even handler </p>


```aidl
// in the menu page
@Override
public void mouseClicked(float mx, float my) {
    if (playButton.contains(mx, my)) {
      UiManager.getInstance().setPage("game");
    } else if (highScoreButton.contains(mx, my)) {
      UiManager.getInstance().setPage("highscore");
    }  else if (newPageButton.contains(mx, my)) {
      UiManager.getInstance().setPage("newPage");
    }
}    
```

<p> now you can add any text or buttons you want to the new page in the constructor.</p>
<p> the following is a sample to see what it would look like:</p>

```aidl
public class MenuPage extends Frame {


  private final Button newButton;

  public MenuPage(Window parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height);
    
    float newButtonWidth = 300;
    newButton = new Button(parent, x + width/2 - newButtonWidth / 2, y + height/2, newButtonWidth, 75, "NEW");
    
    add(newButton);
    add(new Text(parent, x + width/2, y + height/2 - 100, "New Page", 50));
  }
 
  @Override
  public void draw() {
      super.draw();
  }
    
  @Override
  public void mouseClicked(float mx, float my) {
      if (newButton.contains(mx, my)) {
        UiManager.getInstance().setPage("nextPage");
      }
 } 
    
```
<h3>Reflection on my design decisions and what I would do differently next time.</h3>
<h4>Overview</h5> 
<p>
    Overall I am comtempt with the UI system that I have created here. There are many very obvious things that could be
made better. But there are some things that I am happy with that I will cover.
</p>

<h4> The good </h4>
<p> 
    I am happy with how I was able to use the composite pattern. I find it very cool to learn something in class and
then use it in a project.
</p>
<p> Although I wasn't able to use the pattern perfectly, the general idea is there and I learned a lot abou 
making a ui system that has multiple pages and many elements per page.
</p>
<h4> the bad</h4>
<p> 
    There are a a few little problems that I noticed but didn't have enough time to fix
</p>
<ol> 
    
<li>
    <p> The making of the buttons is inconsistent. <p>
    <p>
        Sometimes I am just using the button class and sometimes I am creating a new class and extending it 
        off of button.
    </p>
    
</li> 

<li>
    <p> Make the event handle for button better. </p>
    <p> My main idea for the buttons was to have them in the components and not be an attribute of the class
    I ended up making the buttons attributes because I needed to add them to the even handler.
    The ideal solution would make this not necessary. 
    The only thing that the buttons do are change the page. With this in mind I would've given button an 
attribute that holds the destination and calls the

UiManager.getInstance().setPage(destination) 

method to change the page so they dont have to be indivually added to the event handle for the page. 
    </p>
</li>

</ol>

<h4> Conclusion</h4>
<p> I learned a lot about how GUI's work and the amount of design that needs to go into a good ui system.
I'm proud of what I was able to make. In the future I would like to make another with lots of improvments to the design.
</p>

<h4>My main contribution to the UML</h4>
![alt text](UIUML.png)




## Kavin
<ul>
  <li>Designer (Levels, Sprites, Grid)</li>
  <li>Background Music</li>
  <li>Refactoring</li>
  <li>CheckStyle</li>
  <li>Helped with UI Manager, UI Component, and Score Systems</li>
        
        
</ul>

## Kale
<h3>The Game Creation God</h3>
<h4> Individual pitch</h4>
<p>
I designed and created the logic to handle in-game logic events, anamation events, time dependecy for game updates, frame dependency for animations, and contributed to refactoring snake, snake body, window and sprite manager 
</p>
<h4>primary contributions</h4>
<ul>
  <li>Created methods and logic for Snake</li>
  <li>Created methods and logic for Food</li>
  <li>Created methods and logic for SnakeBody</li>
  <liCreated methods and logic for Wall></li>
  <li>Implemented clock logic to ensure game logic is time dependent and not frame dependent</li>
  <li>Implemented user controls</li>
  <li>Orginized images into hashmap for faster access</li>
  <li>wrote logic to reset the game</li>
  <li>wrote logic to get hardware screensize and create game screen based off this</li>
  <li>created logic for collisions between objects</li>
  <li>multithreaded draw method for smoother frames on computers with lower single core speed</li>
  <li>created classic and modern game modes</li>
  <li>built level editor for standardized level creation</li>
  <li>wrote methods for reading files produced with level editor</li>
  <li>Decoupled snake and snake body</li>
  <li>Decoupled window and sprite manager</li>
</ul>
<h3>Reflection on my design decisions and what I would do differently next time.</h3>
<h4>Overview</h5> 
<p>
    I am mostly happy with the portion that i worked on but there are a few glaring issue that I would have fixed given more time.
</p>

<h4> The good </h4>
<p>I am mostly happy with how the snake turned out and proud of how the body is moving and growing. I am very proud of the multi-threading as it enabled me to be able to actually run the game on my own laptop!</p>
<h4> The bad </h4>
<p>Given more time i would have implemented a buffer for the control input to make inputs feel smoother, as some key presses are missed when the game is already executing logic for a keypress. I would ghave liked the solve the issue with the snakes head deteching from the body and the snakes body not appearing in the right location when it first begins moving.</p>

<h4> Conclusion</h4>
<p> I increased my understanding of object oriented design, learned the issue with over coupling, and gained an understand of how to optimize Java with multi-threading. 
</p>

## Adam
<h4> Individual pitch</h4>
<p>
I designed and created the all of the test classes created in the project. These were for all significant classes that have been created. I helped in the redesign of any classes that I felt could be made differently due to knowledge found in my tests. I also helped in finding major bugs, one of them would be in the window class when we were trying to load images before the game had even started. 
</p>
<ul>
  <li>Created all project tests</li>
  <li>Refactoring</li>
  <li>fixed program wide problems (Window class)</li>
</ul>

<h4> The good </h4>
<p>I am happy that all of our tests pass LOL. I would be worried if any tests didn't pass. But largely i'm so happy that I now understand how tests work when tdd is not in place. Knowing that tests have to be constantly changed in agile development was really cool and something I didn't understand before. I am also happy at how some minor bugs were found through the tests like missing getters.</p>
<h4> The bad </h4>
<p>I would've liked to use reflection in my tests for some private classes. I am always trying to push my work to the absolute max and being able to understand the hardest concepts in testing would've been nice. I would also say that test classes that used p image could of been made much better.</p>

<h4> Conclusion</h4>
<p> I increased my understanding of testing and how to create tests in a non test driven development practice. I also learned how to stay calm and use different methods in finding bugs in code on the main branch. 
</p>

## Harrison
<ul>
  <li>March 23, 2022
        <ul>
        <li>Made the drawable interface</li>
        <li>Updated the READme</li>
        <li>Made the original snake class and singleton</li>
        <li>Made the original snakebody and functionality</li>
        <li>Helped add functionality to the home button UI</li>
        <li>Made self-managing queue data structure for the beetle enemies that adds enemies when enough die</li>
        <li>Implemented all the enemy behaviour and code</li>
        <li>Implemented the enemy spawning system</li>
        </ul>

</li>
</ul>

<h2> Our UML for the Project</h2>
![alt text](UML.png)

# Project

## Technical Project Requirements

The minimum requirements for the project are outlined here to give you a starting point. Meeting the minimum requirements alone will not guarantee you a good mark. You are welcome to meet and exceed the minimum requirements if you have good, creative ideas and would like to discuss them with me.

**Requirement 1**: The project must incorporate some visual interface using Processing.org libraries. All user interaction must be conducted via this interface.

**Requirement 2**: The project must incorporate some kind of non-blocking concurrent/asynchronous processing that happens at regular intervals. For example, you might push or fetch data from in the background.

**Requirement 3**: The project must incorporate some kind of non-trivial persistent data state that must be read, processed, and written at regular intervals. For example, you might save a game state in a JSON file. This may or may not be included with Requirement 2.

**Requirement 4**: The project must incorporate some kind of self-managing custom iterable data structure. For example, you might have a collection of enemies that are added and deleted based on statistics maintained by the data structure.

**Requirement 5**: The project must be well-documented, complete, and run without errors on final submission.

## Project Pitch (group, 1%)

The project pitch will be a short document that describes the kind of interactive application you would like to create with your group. The project pitch must include the following items:

*One-liner*: One-sentence description of your project.
*Outline*: 1-10 sentences that describe how your project will fulfill the project requirements.
*Communication policies*: A description of how your group will meet, communicate, and make decisions (as per Lab 03).
*Roles and responsibilities*: A description of each team member's jobs in the group.
*Milestones*: A rough outline of the major project milestones that you expect to complete and your own estimated timeline. This can and will change, so do your best to estimate and plan for the milestones to change.

Draft was due today, final due next lab. Submission here, on GitHub. Make a `.md` file that outlines the above.

## Initial UML Diagrams (group, 1%)

The initial UML diagrams will outline the class structure that your group will follow for the first milestone of the project. It must include the following items all classes that will be created by the group and important descriptive interfaces from either the Java library or created by the group. I expect that this will change significantly throughout the project, so it does not have to be perfect but it should be a best effort attempt. This is because you will use this to communicate with your group members about what to make. Therefore, the diagram should be *sufficiently complex* to give you a term's worth of work.

Draft due next Lab, final due two labs from now. Submission here, on GitHub. Suggestion is to use a tool like [draw.io](https://app.diagrams.net/) but you may use whatever tool is most useful for you.

## Initial GitHub Issues (group, 1%)

The initial GitHub issues will be the tasks that are assigned to each of your group members at the beginning of the project. Every team member should have at least five issues to start (20-30 total). You will have to decide within your group how granular you want to make these issues.

Issues will be tracked here, on GitHub.

## Final Project Demo (group, 1%)

The Final Project Demo will be a working version of your project that you will present to your lab section for review. The demo will be in lab, and will include a live demo of the working application, and a short code review. There are no slides required, but you should have practiced your demo to make sure it will run reasonably well. This will be during the last lab of class.

No submission.

## Final README.md (group, 1%)

The Final README.md must give instructions on how to run your program, a list of contributions by each member, and any references/citations for code you may have used from elsewhere.

Submission is here, on GitHub, in the `README.md` file.

## Final Product (group, 5%)

The Final Product will be evaluated for overall code design and documentation and evaluated on the same design principles as individual contributions. If you are below the 1000 line minimum contribution, your mark will be scaled down for this portion.

Submission is here, on GitHub.

## Code Contributions (individual, 15%)

You will be expected to take on a significant individual contribution to the group project (at least 1000 lines of non-trivial code). It may be in a number of forms, but here are some examples:

**Architect**: you are in charge of the high-level code structuring and organizing.

**Test maker**: you are in charge of test coverage that supports other group members.

**UI/UX lead**: you design and implement the user interface.

**Backend**: you design and implement the data structures.

**…??**: Make up your own depending on your use case, i.e., collision system designer, animation architect, async code wrangler.

Contributions must be for functional, working Java code and must be continuous throughout the term. You may not, for example, push all of your changes at the end of term. Code will be marked on following good design principles, i.e., SOLID, design patterns, etc. You are encouraged to work together and use pair programming for components, but you will be marked on your contribution to your own modules individually.

## Documentation Contributions (individual, 5%)

Your code must be well-documented with fully-formed method signatures, comments, and necessary README or Wiki pages. This is further broken down into the following.

### Initial individual pitch (1%)
A description of your individual feature that you plan to implement.

Due date TBD.

### Initial individual UML Diagrams (1%)
Any combination of sequence, communcation, or class diagrams that describe your feature's initial planned abilities.

Due date TBD.

### Documentation contributions (3%)
Your personal feature documentation, wherever it happens to show up in the final documentation.

Due with final submission.

## Issues and Pull Request Contributions (individual, 5%)
You must track your own work in the form of creating and closing GitHub issues, creating and reviewing pull requests, responding to issues that have been assigned to you, and creating issues that you assign to others (all within reason).

# Errata
The project MUST be managed here, in this GitHub repo. Nothing that happens outside of this GitHub repo will be trackable by me, therefore, it will not be marked or considered for marking.

You must use the following branching structures:
- `main` branch must always be working, tested, debugged, human-readable code.
- `<initals>_<issue #>_<optional description>` is the format for each ISSUE that you're working on. It should always be a branch off of `main`.
- Every individual branch must regularly merge `main`, and it should be no more than a few days before your branch is either merged into `main` or deleted.

You must use pull requests to manage your code integration:
- make your branch from `main`, e.g., `git checkout -b pb_71_demo`
- make your commits, e.g., `git add .` and `git commit -m "fixed issue 71 by reloading gradle for the 100th time"` and `git push origin pb_71_demo`.
- merge `main` into your branch, and NOT the other way around. E.g., `git merge main`. YOU fix all the merge conflicts or problems that arise. Commit and push again.
- go to GitHub.com and make a pull request
- one other person (NOT YOU) needs to review the code and either approve or reject your changes with detailed line-by-line comments.
- If needed, make the requested fixes and commit and push again.
- the other person (NOT YOU) will merge your PR into `main`
- the other person (NOT YOU) will delete your branch

