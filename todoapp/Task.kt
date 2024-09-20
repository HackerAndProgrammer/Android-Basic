package com.example.androidmaster.todoapp

data class Task(val id: Int, val name: String, var isSelected: Boolean, val category: TaskCategory)
