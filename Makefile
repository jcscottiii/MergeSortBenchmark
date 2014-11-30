app-debug.apk: MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk
	mv MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk app-debug.apk

MergeSortBenchmarkApp/app/build/outputs/apk/app-debug.apk: MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java
	cd MergeSortBenchmarkApp && ./gradlew assembleDebug

MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so: go_gomergesort/go_gomergesort.go
	CGO_ENABLED=1 GOOS=android GOARCH=arm go build -o libgomergesort.so -ldflags="-shared" .
	mkdir -p MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/
	cp libgomergesort.so MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so

MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java:
	mkdir -p MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/
	gobind -lang=java . > MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java

go_gomergesort/go_gomergesort.go:
	mkdir -p go_gomergesort
	gobind -lang=go . > go_gomergesort/go_gomergesort.go

clean:
	rm go_gomergesort/go_gomergesort.go
	rm MergeSortBenchmarkApp/app/src/main/java/go/gomergesort/Gomergesort.java
	rm MergeSortBenchmarkApp/app/src/main/jniLibs/armeabi-v7a/libgomergesort.so
	rm app-debug.apk
