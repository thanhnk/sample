export MAVEN_OPTS="-Xms256m -Xmx1024m -XX:MaxPermSize=128m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -XX:+DisableExplicitGC -Xverify:none"

if [ `ulimit -n` -le 2048 ] 
then
    echo "The maximum number of open files is set to `ulimit -n`. Magnolia bootstrapping"
    echo "opens quite a lot of files. You may consider to change this limit in by adding"
    echo "the following two lines to your /etc/security/limits.conf."
    echo
    echo '* soft nofile 4096'
    echo '* hard nofile 4096'
    echo
    echo 'The following needs to be appended to  /etc/pam.d/common-session.'
    echo
    echo 'session required pam_limits.so'
    echo
fi

mvn -o compile war:exploded tomcat7:run-war-only -Ptomcat,rapid -Dmagnolia.logs=./logs
