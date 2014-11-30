app-debug.apk: MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk
	mv MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk app-debug.apk

MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk: MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java
	cd MergeSortBenchmarkApp && ./gradlew assembleDebug

MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so: mergesort/go_mergesort.go
	CGO_ENABLED=1 GOOS=android GOARCH=arm go build -o libgomergesort.so -ldflags="-shared" . && cd ../
	mkdir -p MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/
	mv libgomergesort.so MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so

MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java:
	mkdir -p MergeSortBenchmarkApp/app/src/main/java/go/mergesort/
	cd mergesort && CGO_ENABLED=0 gobind -lang=java . > ../MergeSortBenchmarkApp/app/src/main/java/go/mergesort/Mergesort.java

mergesort/go_mergesort.go:
	mkdir -p mergesort/go_mergesort
	cd mergesort && CGO_ENABLED=0 gobind -lang=go . > go_mergesort/go_mergesort.go

clean:
	rm mergesort/go_mergesort/go_mergesort.go
	rm MergeSortBenchmarkApp/app/src/main/java/go/mergesort/Mergesort.java
	rm MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so
	rm app-debug.apk
