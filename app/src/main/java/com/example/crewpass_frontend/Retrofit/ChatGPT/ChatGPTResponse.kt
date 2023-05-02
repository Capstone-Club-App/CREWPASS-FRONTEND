package com.example.crewpass_frontend.Retrofit.ChatGPT

data class ChatGPTRequest(
    val prompt: String,
    val max_tokens: Int
)

data class ChatGPTResponse(
    val choices: List<ChatGPTChoice>
)

data class ChatGPTChoice(
    val text: String,
    val index: Int,
    val logprobs: ChatGPTLogprobs
)

data class ChatGPTLogprobs(
    val tokens: List<String>
)
