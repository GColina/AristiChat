package com.gcolina.aristichat.data.network

import com.gcolina.aristichat.data.network.dto.MessageDto
import com.gcolina.aristichat.data.network.response.MessageResponse
import com.gcolina.aristichat.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "message"
    }

    fun sendMsgToFirebase(messageDto: MessageDto) {
        val newMessage = reference.child(PATH).push()
        newMessage.setValue(messageDto)
    }
    fun getMessages(): Flow<List<MessageModel>> =
        reference.child(PATH).snapshots.map { dataSnapshot ->
            dataSnapshot.children.mapNotNull { dataSnapshotChildren ->
                dataSnapshotChildren.getValue(MessageResponse::class.java)?.toDomain()
            }
        }
}
