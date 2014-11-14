module.exports = function(grunt) {
  grunt.initConfig({
    web_server: {
      options: {
        cors: true,
        port: 10080,
        nevercache: true,
        logRequests: true
      },
      foo: 'bar' // For some reason, an extra key with a non-object value is required
    },
    less: {
      development: {
        options: {
          compress: true,
          yuicompress: true,
          optimization: 2
        },
        files: {
          "css/styles.css": "less/styles.less"
        }
      }
    },
    watch: {
      styles: {
        files: ['less/**/*.less'],
        tasks: ['less'],
        options: {
          nospawn: true
        }
      }
    },
    copy: {
      css: {
        bootstrap: {
          src: "bower_components/bootstrap/dist/css/bootstrap.min.css",
          dest: "css/bootstrap.min.css"
        }
      },
      js: {
        bootstrap: {
          src: "bower_components/bootstrap/dist/js/bootstrap.min.js",
          dest: "js/bootstrap.min.js"
        }
      }
    }
  });

  grunt.loadNpmTasks('grunt-web-server');
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-copy');

  // Default task(s).
  grunt.registerTask('default', [ 'web_server' ]);
};
