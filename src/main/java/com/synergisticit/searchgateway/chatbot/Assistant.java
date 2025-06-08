package com.synergisticit.searchgateway.chatbot;

import com.synergisticit.searchgateway.repository.HotelRepository;
import com.synergisticit.searchgateway.repository.HotelRoomRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Assistant {

    @Autowired HotelRepository hotelRepository;
    @Autowired HotelRoomRepository hotelRoomRepository;

    @Bean
    public ChatClient client(ChatClient.Builder builder,
                             VectorStore vectorStore,
                             InMemoryChatMemory inMemoryChatMemory
//                             HotelRoomRepository hotelRoomRepository,
//                             HotelRepository hotelRepository
    ) {

        // System tells Ai what the role, purpose, and context of the Ai is.
        var system = "You are an Ai powered assistant to help people to find hotels! You should be able to answer " +
                "questions about hotels and provide recommendations based on user queries. You should also " +
                "be able to provide information about all details. Regarding the context, " +
                "you should be able to understand the user's query and provide the best possible answer " +
                "based on the user's query. You can access the database and combine with your own knowledge to " +
                "provide the best possible answer to the user's query as well.";
        // search in database
        hotelRoomRepository.findAll().forEach(hotelRoom -> {
            var formatted = "roomId: %s, numberRooms: %d, price: %f, discount %f, description %s, policies %s"
                    .formatted(hotelRoom.getHotelRoomId(), hotelRoom.getNoRooms(), hotelRoom.getPrice(),
                            hotelRoom.getDiscount(), hotelRoom.getDescription(), hotelRoom.getPolicies());
            var hotelRoomFound = new Document(formatted);
            vectorStore.add(List.of(hotelRoomFound));
        });

        hotelRepository.findAll().forEach(hotel -> {
            var formatted = "name: %s, city: %s, state %s, price: %f, discount %f, stars %s, description %s"
                    .formatted(hotel.getHotelName(), hotel.getCity(), hotel.getState(), hotel.getAveragePrice(),
                            hotel.getDiscount(), hotel.getStarRating(), hotel.getDescription());
            var hotelsFound = new Document(formatted);
            vectorStore.add(List.of(hotelsFound));
        });
        
        // retrieve augmented vector store generation
        return builder.defaultSystem(system).defaultAdvisors(
                new MessageChatMemoryAdvisor(inMemoryChatMemory),
                new QuestionAnswerAdvisor(vectorStore)).build();
    }

    
    @Bean
    public InMemoryChatMemory inMemoryChatMemory() {
        return new InMemoryChatMemory(); // You can configure it here if needed
    }
}




// maybe like this for return documents

//@Bean
//ApplicationRunner runner() {
//    return args -> {
//        // 1. Call the AI client to get the content
//        var content = client.prompt().user("do you have any hotels in Columbia SC?")
//                              .call().content();
//
//        System.out.println("AI Response: " + content);
//
//        // 2. Generate the embedding for the query
//        float[] queryEmbedding = hotelSearchService.generateEmbedding(content);
//
//        // 3. Perform semantic search using the query embedding
//        List<Hotel> matchingHotels = hotelSearchService.searchHotels(queryEmbedding);
//
//        // 4. Return or process the matching hotels
//        matchingHotels.forEach(hotel -> {
//            System.out.println("Hotel Found: " + hotel.getName() + ", Location: " + hotel.getLocation());
//        });
//    };
//}