# Exercises

# Front-end

The following instructions allow you to run the exercises in the front-end directory on your local machine:
1. Install [Node.js](http://nodejs.org) (On a Mac, it's recommended that you do this using [Homebrew](http://brew.sh) but any method will work).

  Node.js is required by most of the rest of the front-end tools that you'll use
2. In a terminal window, `cd` into the front-end directory:
`cd front-end`
3. Install the project's Node.js dependencies by executing the following command:
`npm install`

  The project's Node.js dependencies are specified in the `package.json` file and include things like [Grunt](http://gruntjs.com) - A task runner that's used to do everything from serving static/prototype pages via HTTP while you work on them to compiling CSS preprocessor files into CSS and minifying/concatenating the resulting CSS - and the Grunt plugins that you'll use.

  The project's Node.js dependencies are installed in the `node_modules` directory (in the same directory as the `packages.json` file)
4. Install [Bower](http://bower.io) globally by executing the following command:
`npm install -g bower`

  Bower is a dependency manager that's used to install and manage the projects' CSS and JavaScript dependencies (such as [Bootstrap](http://getbootstrap.com), [jQuery](http://jquery.com), etc.)
5. Install the project's CSS and JavaScript dependencies by executing the following command:
`bower install`

   This installs the project's CSS and JavaScript dependencies in the `bower_components` directory (in the same directory as the `bower.json` file)
6. Execute the command to continuously compile the project's [Less](http://lesscss.org) files whenever they change:
`grunt watch`
7. In a different terminal window, execute the command to serve the exercise pages via HTTP:
`grunt`
8. Visit [http://localhost:10080/](http://localhost:10080) and click on the link to the page you want to visit (such as [http://localhost:10080/index.html](http://localhost:10080/index.html))
