for arg; do
    case "$arg" in
    all)   javac *.java ;;
    go)    java Main ;;
    clean) rm -f *.class ;;
    jar)   jar -cmf manifest.txt nature-sim.jar *.class ;;
    run)   java -jar nature-sim.jar ;;
    esac
done
