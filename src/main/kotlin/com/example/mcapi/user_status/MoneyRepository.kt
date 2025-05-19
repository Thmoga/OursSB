package com.example.mcapi.user_status

import org.springframework.data.jpa.repository.JpaRepository

interface MoneyRepository : JpaRepository<Money, String>
