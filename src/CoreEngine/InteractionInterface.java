package CoreEngine;

public interface InteractionInterface {
	String NPC = 	"Introduce Yourself" + 				//(0) npc gets to know you
					"Get Information" +					//(1) see NPC_GET_INFORMATION
					"Trade" +							//(2) trade with npc
					"Converse" +						//(3) get friend list and secret of npc
					"Gossip" +							//(4) give secret of someone else to npc
					"Pickpocket" +						//(5) pickpocket npc
					"Offer Job" +						//(6) hire npc to work for you
					"Duel" +							//(7) challenge npc to a duel
					"Threaten" +						//(8) demand gold/items/resource from npc
					"Invite to Group" +					//(9) invite to your group
					"Influence" +						//(10) request npc to give you good reputation to another npc (increases npcs friendship towards you)
					"Propose Marriage";					//(11) Ask their hand in marriage
	String NPC_GET_INFORMATION = 
					"Ask for Help" +					//(0) Get information about game mechanics
					"Ask for Directions";				//(1) Get information about world map
}