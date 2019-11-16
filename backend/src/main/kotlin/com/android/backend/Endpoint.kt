package com.android.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class Endpoint {

    @Autowired
    lateinit var storage: Storage

    @RequestMapping("/save", method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody a: Message) {
        println("asdasdasd"+a.toString())
        storage.messages.add(a.message.toString())
        println(storage.messages)
    }

    @RequestMapping("/get")
    fun get() : ArrayList<String> {
        return storage.messages
    }

}