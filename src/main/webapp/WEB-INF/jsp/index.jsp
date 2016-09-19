
<html>

<body>
<form>
    <button type="submit" formaction="/project/save/">Add Project</button>
    <br>
    <button type="submit" formaction="/project/getall">Projects</button>
    <br>
    <button type="submit" formaction="/project/generator">Generator</button>
</form>

<h4>Instruction:</h4>
<output>

    <br>
    1. Run Mongo DB<br>
    2.Create project by filling fields in menu <b>Add Project:</b><br>
    2.1 <b>Name</b> - it`s desirable to be as a real project name for conviniency;<br>
    2.2 <b>RootPath</b> - input path to the root package, where your classes is stored.
    Example: C:\Users\...myproject...\src\main\java\com\account\shared<br>
    If you want to access all project classes - input a path to the high-level package.
    Example: C:\Users\...myproject...\myproject\src\main\java\com<br>
    2.3 <b>JarPath</b> - you need to build yor project to get JarPath like this:
    C:\Users\...myproject...\build\libs\myproject.jar<br>
    2.4 <b>DomainPath</b> - is used when you want to get Controller Methods, but you may leave it empty, if you don`t nedd this function<br>
    <br>
    3. Press <b>Projects</b> to see all projects in database.<br>
    <br>
    4. Press <b>Generator</b> to use the main function of this program - create Json-like documentation for your use or get Controller Methods.
</output>
</body>

</html>