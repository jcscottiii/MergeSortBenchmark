package main

import (
	"golang.org/x/mobile/app"

	_ "golang.org/x/mobile/bind/java"
	_ "github.com/jcscottiii/mergesort_benchmark/mergesort/go_mergesort"
)

func main() {
	app.Run(app.Callbacks{})
}
