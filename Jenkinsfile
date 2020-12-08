node
{

def mavenHome = tool name: "maven3.6"
    
stage ('CodeCheckOut')    
    {
        git branch: 'master', credentialsId: 'GIT', url: 'https://github.com/frameworksselenium/Agile_BDD.git'
    }

stage ('Compile Stage')
    {
        sh "${mavenHome}/bin/mvn clean install"
    }

stage ('Test Stage')
    {
        sh "${mavenHome}/bin/mvn test"
    }

stage ('Cucumber Reports')
    {
       cucumber buildStatus: "UNSTABLE",
       fileIncludePattern: "**/cucumber.json",
       jsonReportDirectory: 'target'
     }

stage ('Cucumber Original Reports')
         {
            sh 'chmod -R 777 ${WORKSPACE}'
            sh 'cd ${WORKSPACE}/target/cucumberReport'
            publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: "${WORKSPACE}/target/cucumberReport",
                    reportFiles: '*.html',
                    reportName : 'Cucumber Original Report'
            ])
          }
}
