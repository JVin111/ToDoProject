package com.example.todoproject.ui.components

class Padding(val left: Int?, val top: Int?, val right: Int?, val bottom: Int?) {

    constructor(vertical: Int? = null, horizontal: Int? = null) : this(horizontal, vertical, horizontal, vertical)

    constructor(all: Int) : this(all, all, all, all)
}