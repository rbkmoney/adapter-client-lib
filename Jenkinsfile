#!groovy
build('adapter-client-lib', 'docker-host') {
    checkoutRepo()
    loadBuildUtils()

    def javaLibPipeline
    runStage('load JavaLib pipeline') {
        javaLibPipeline = load("build_utils/jenkins_lib/pipeJavaLib.groovy")
    }

    def buildImageTag = "bc95d0d6dc13c693acd2b274531a7d604b877bf3"
    javaLibPipeline(buildImageTag)
}
