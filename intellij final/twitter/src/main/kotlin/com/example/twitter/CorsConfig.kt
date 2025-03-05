//package com.example.twitter
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import org.springframework.web.filter.CorsFilter
//
//@Configuration
//class CorsConfig {
//    @Bean
//    fun corsFilter(): CorsFilter {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowedOrigins = listOf("*") // 앱에서 요청 가능하도록 허용
//        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        config.allowedHeaders = listOf("*")
//        source.registerCorsConfiguration("/**", config)
//        return CorsFilter(source)
//    }
//}
