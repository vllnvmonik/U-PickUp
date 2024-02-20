package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.StudentDetailsResponse
import com.echelon.upickup.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    private val studentRepository = StudentRepository()

    private val _studentDetails = MutableStateFlow<StudentDetailsResponse?>(null)
    val studentDetails: StateFlow<StudentDetailsResponse?> = _studentDetails

    fun getStudentDetails(id: Int) {
        viewModelScope.launch {
            try {
                val response = studentRepository.getStudentDetails(id)
                if (response.isSuccessful){
                    val details = response.body()
                    _studentDetails.value = details
                    if (details != null){
                        val id = details.student.id
                        val studentId = details.student.student_id
                        val email = details.student.email_ad
                        val firstName = details.student.first_name
                        val middleName = details.student.middle_name
                        val lastName = details.student.last_name
                        val age = details.student.age
                        val gender = details.student.gender
                        val department = details.student.department
                        val program = details.student.program

                        Log.d(
                            "ProfileViewModel",
                            "Get successful. , User ID: $id, Student ID: $studentId, Email: $email, First Name: $firstName, Middle Name: $middleName, Last Name: $lastName, Age: $age, Gender: $gender, Department: $department, Program: $program"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching student details: ${e.message}", e)
            }
        }
    }
}