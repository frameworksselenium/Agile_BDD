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
        echo "Work space::::: ${WORKSPACE}"
        echo "Tags for execution::::: ${Tags}"
        echo "Execution Environment::::: ${ExecutionEnvironment}"
        echo "No of threads for execution::::: ${Threads}"

        sh "${mavenHome}/bin/mvn clean test -DExecutionEnvironment=${ExecutionEnvironment} -Dcucumber.filter.tags=${Tags} -Dtestng.threadcount=${Threads}"
    }

stage ('Cucumber Reports')
    {
       cucumber buildStatus: "UNSTABLE",
       fileIncludePattern: "**/cucumber.json",
       jsonReportDirectory: 'target'
     }
}