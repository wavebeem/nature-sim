for arg; do
    case "$arg" in
    all)   javac -Xlint:unchecked *.java ;;
    go)    java Main ;;
    clean) rm -f *.class ;;
    jar)   jar -cmf manifest.txt nature-sim.jar *.class resources/ ;;
    run)   java -jar nature-sim.jar ;;
    esac
done
