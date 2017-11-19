package dal

interface MongoRepositoryBase {
    companion object {
        const val USERS_COLLECTION = "users"
    }
}