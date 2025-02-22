
package mage.cards.t;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.effects.keyword.InvestigateEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterControlledPermanent;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author fireshoes
 */
public final class TamiyosJournal extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("Clues");

    static {
        filter.add(SubType.CLUE.getPredicate());
    }

    public TamiyosJournal(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{5}");
        this.supertype.add(SuperType.LEGENDARY);

        // At the beginning of your upkeep, investigate (Create a colorless Clue artifact token with \"{2}, Sacrifice this artifact: Draw a card.\").
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new InvestigateEffect()));

        // {T}, Sacrifice three Clues: Search your library for a card and put that card into your hand. Then shuffle your library.
        Ability ability = new SimpleActivatedAbility(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(), false, true), new TapSourceCost());
        ability.addCost(new SacrificeTargetCost(3, filter));
        this.addAbility(ability);
    }

    private TamiyosJournal(final TamiyosJournal card) {
        super(card);
    }

    @Override
    public TamiyosJournal copy() {
        return new TamiyosJournal(this);
    }
}
