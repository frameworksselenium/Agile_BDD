node
{

def mavenHome = tool name: "maven3.6"
    
stage ('CodeCheckOut')    
    {
        git branch: 'master', credentialsId: 'GIT', url: 'https://github.com/frameworksselenium/Agile_BDD.git'
    }

stage ('Compile Stage')
    {
        sh "${mavenHome}/bin/mvn clean install -DskipTests"
    }

stage ('Test Stage')
    {

        sh "${mavenHome}/bin/mvn -Dcucumber.options =--tags=@all test"
    }

stage ('Cucumber Reports')
    {
       cucumber buildStatus: "UNSTABLE",
       fileIncludePattern: "**/cucumber.json",
       jsonReportDirectory: 'target'
     }
}