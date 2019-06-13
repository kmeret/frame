package io.github.kmeret.frame.infrastructure.coroutines

import kotlinx.coroutines.Job

class CompositeJob {
    private val map = hashMapOf<String, Job>()

    fun add(job: Job, key: String = job.hashCode().toString()) = map.put(key, job)?.cancel()

    fun clear() = map.forEach { it.value.cancel() }
}