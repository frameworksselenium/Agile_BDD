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
        echo "Test Arguments:"
        echo "-DEnvironment = ${Environment}"
        echo "-Dcucumber.options = -tags ${Tag}"
        echo "-Dthreadcount = ${Threads}"
        sh "${mavenHome}/bin/mvn test -DEnvironment = ${Environment} -Dcucumber.options = --tags ${Tag} -Dthreadcount = ${Threads}
    }

stage ('Cucumber Reports')
    {
       cucumber buildStatus: "UNSTABLE",
       fileIncludePattern: "**/cucumber.json",
       jsonReportDirectory: 'target'
     }
}