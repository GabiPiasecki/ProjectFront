import "./HomePage.css";
import { useState } from 'react';
import {
  Carousel,
  CarouselItem,
  CarouselControl,
  CarouselIndicators,
  CarouselCaption
} from 'reactstrap';
import coupons from "../../../../Assets/coupons.png"
import sale from "../../../../Assets/sale.jpeg"
import cart from "../../../../Assets/cart.jpg"

const items = [
    {
      src: coupons,
      altText: 'Welcome',
      title: "Welcome to our coupons site",
      caption: "Purchase Now!"
    },
    {
      src: cart,
      altText: 'Slide 2',
      title: "Our deals are the best in the class!",
      caption: "Better than Noam's"
    },
    {
      src: sale,
      altText: 'Slide 3',
      title: "Crazy sales RIGHT NOW",
      caption: 'SALE SALE SALE'
    }
  ];

function HomePage(): JSX.Element {
    const [activeIndex, setActiveIndex] = useState(0);
    const [animating, setAnimating] = useState(false);
  
    const next = () => {
      if (animating) return;
      const nextIndex = activeIndex === items.length - 1 ? 0 : activeIndex + 1;
      setActiveIndex(nextIndex);
    }
  
    const previous = () => {
      if (animating) return;
      const nextIndex = activeIndex === 0 ? items.length - 1 : activeIndex - 1;
      setActiveIndex(nextIndex);
    }
  
    const goToIndex = (newIndex:number) => {
      if (animating) return;
      setActiveIndex(newIndex);
    }
  
    const slides = items.map((item) => {
      return (
        <CarouselItem
          onExiting={() => setAnimating(true)}
          onExited={() => setAnimating(false)}
          key={item.src}
        >
          <img src={item.src} alt={item.altText} />
          <CarouselCaption className="text-dark" captionText={item.caption} captionHeader={item.title} />
        </CarouselItem>
      );
    });
  
    return (
      <Carousel
        activeIndex={activeIndex}
        next={next}
        previous={previous}
      >
        <CarouselIndicators items={items} activeIndex={activeIndex} onClickHandler={goToIndex} />
        {slides}
        <CarouselControl direction="prev" directionText="Previous" onClickHandler={previous} className="text-dark"/>
        <CarouselControl direction="next" directionText="Next" onClickHandler={next} className="text-dark"/>
      </Carousel>
    );
}
      

export default HomePage;
